package com.mygdx.game.Game2D.Manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Game2D.Entities.Entity;
import com.mygdx.game.Game2D.Entities.player.Player;
import com.mygdx.game.Game2D.Utils.PasswordHashing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class ProfileManager {
    private final Preferences preferences;
    private ArrayList<Player> profiles;
    private Player currentPlayer;
    DatabaseManager databaseManager;

    public ProfileManager(){
        preferences = Gdx.app.getPreferences("OOP2FinalProject");

        String profilesJson = preferences.getString("profiles", null);
        databaseManager = DatabaseManager.getInstance();

        if (profilesJson != null) {
            profiles = jsonToProfiles(profilesJson);
        } else {
            profiles = new ArrayList<>();
        }

    }

    public boolean addProfile(Player player){
        for (Player p : profiles) {
            if(p.username.equals(player.username)){
                return false;
            }
        }
        profiles.add(player);
        saveProfiles();
        return true;
    }

    public void saveProfiles() {
        Json jsonParser = new Json();
        String profilesJson = jsonParser.toJson(profiles);
        preferences.putString("profiles", profilesJson);
        preferences.flush();
    }

    private ArrayList<Player> jsonToProfiles(String json) {
        ArrayList<Player> profiles = new ArrayList<>();

        Json jsonParser = new Json();
        JsonValue root = new JsonReader().parse(json);

        for (JsonValue profileValue : root) {
            Player player = jsonParser.readValue(Player.class, profileValue);
            profiles.add(player);
        }

        return profiles;
    }

    public void saveProfile(Player player) {
        boolean exists = false;

        for (Player p: profiles) {
            if(Objects.equals(p.username, player.username)) {
                p.setPosition(new Vector2(player.getPosition().x, player.getPosition().y));
                p.setDirection(p.getDirection());
                p.setMap(player.map);
                exists = true;
                break;
            }
        }

        if (!exists) {
            profiles.add(player);
        }

        cloudProfileUpload(player);

        saveProfiles();
    }

    public void overwriteProfile(Player player){
        for(int i = 0; i < profiles.size(); i++){
            if(profiles.get(i).username.equals(player.username)){
                profiles.set(i, player);
            }
        }
        saveProfiles();
    }

    public ArrayList<Player> getProfiles(){
        return profiles;
    }

    public void loadProfile(String username){
        for (Player p : profiles) {
            if(Objects.equals(p.username, username)){
                currentPlayer = p;
                break;
            }
        }
    }

    public Player getCurrentPlayer() {
        currentPlayer.setCollision();
        return currentPlayer;
    }

    public int getProfileIndex(String username){
        for(int i = 0; i < profiles.size(); i++){
            if(Objects.equals(profiles.get(i).username, username)){
                return i;
            }
        }
        return -1;
    }


    public String cloudLogin(String username, String password){
        try(Connection connection = databaseManager.getConnection()){
            String query = "SELECT id FROM users WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            password = PasswordHashing.hashPassword(password);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                preferences.putString("cloud_id",resultSet.getObject("id").toString());
                preferences.flush();
                return "Login success";
            }else {
                return "Invalid credentials";
            }
        }catch (SQLException e){
            return e.getMessage();
        }
    }

    public String cloudRegister(String username, String password){
        try(Connection connection = databaseManager.getConnection()){
            String query = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            password = PasswordHashing.hashPassword(password);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            preparedStatement.executeUpdate();
            return "Register Success";
        }catch (SQLException e){
            if(Objects.equals(e.getSQLState(), "23505")){
                return "Username already taken";
            }
            return e.getMessage();
        }
    }

    public void cloudLogout() {
        preferences.remove("cloud_id");
        preferences.flush();
    }

    public boolean cloudProfileUpload(Player p){
        try (Connection connection = databaseManager.getConnection()) {
            String user_id = preferences.getString("cloud_id");
            if(!preferences.contains("cloud_id"))return false;

            String selectQuery = "SELECT COUNT(*) FROM characters WHERE user_id = ? AND username = ?";
            String insertQuery = "INSERT INTO characters(user_id, username, x, y, direction, map) VALUES (?, ?, ?, ?, ?, ?)";
            String updateQuery = "UPDATE characters SET x = ?, y = ?, direction = ?, map = ?, updated_at = NOW() WHERE user_id = ? AND username = ?";

            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setObject(1, UUID.fromString(user_id));
            selectStatement.setString(2, p.getUsername());
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next() && resultSet.getInt(1) > 0) {
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setFloat(1, p.getPosition().x);
                updateStatement.setFloat(2, p.getPosition().y);
                updateStatement.setString(3, p.getDirection().toString());
                updateStatement.setString(4, p.getMap());
                updateStatement.setObject(5, UUID.fromString(user_id));
                updateStatement.setString(6, p.getUsername());
                int rowsUpdated = updateStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    return true;
                }
                return false;
            } else {
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                insertStatement.setObject(1, UUID.fromString(user_id));
                insertStatement.setString(2, p.getUsername());
                insertStatement.setFloat(3, p.getPosition().x);
                insertStatement.setFloat(4, p.getPosition().y);
                insertStatement.setString(5, p.getDirection().toString());
                insertStatement.setString(6, p.getMap());
                int rowsInserted = insertStatement.executeUpdate();
                if (rowsInserted > 0) {
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean cloudProfilesUpload() {
        String user_id = preferences.getString("cloud_id");

        int uploadProfileCount = 0;

        try (Connection connection = databaseManager.getConnection()) {
            String selectQuery = "SELECT COUNT(*) FROM characters WHERE user_id = ? AND username = ?";
            String insertQuery = "INSERT INTO characters (user_id, username, x, y, direction, map) VALUES (?, ?, ?, ?, ?, ?)";
            String updateQuery = "UPDATE characters SET x = ?, y = ?, direction = ?, map = ?, updated_at = NOW() WHERE user_id = ? AND username = ?";

            for (Player p : profiles) {
                PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
                selectStatement.setObject(1, UUID.fromString(user_id));
                selectStatement.setString(2, p.getUsername());
                ResultSet resultSet = selectStatement.executeQuery();

                if (resultSet.next() && resultSet.getInt(1) > 0) {
                    PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                    updateStatement.setFloat(1, p.getPosition().x);
                    updateStatement.setFloat(2, p.getPosition().y);
                    updateStatement.setString(3, p.getDirection().toString());
                    updateStatement.setString(4, p.getMap());
                    updateStatement.setObject(5, UUID.fromString(user_id));
                    updateStatement.setString(6, p.getUsername());
                    int rowsUpdated = updateStatement.executeUpdate();
                    if (rowsUpdated > 0) {
                        uploadProfileCount++;
                    }
                } else {
                    PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                    insertStatement.setObject(1, UUID.fromString(user_id));
                    insertStatement.setString(2, p.getUsername());
                    insertStatement.setFloat(3, p.getPosition().x);
                    insertStatement.setFloat(4, p.getPosition().y);
                    insertStatement.setString(5, p.getDirection().toString());
                    insertStatement.setString(6, p.getMap());
                    int rowsInserted = insertStatement.executeUpdate();
                    if (rowsInserted > 0) {
                        uploadProfileCount++;
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return uploadProfileCount == profiles.size();
    }


    public boolean cloudProfilesDownload(){
        String user_id = preferences.getString("cloud_id");

        try (Connection connection = databaseManager.getConnection()) {
            String query = "SELECT * FROM characters WHERE user_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1, UUID.fromString(user_id));

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String username = resultSet.getString("username");
                Vector2 position = new Vector2(resultSet.getFloat("x"), resultSet.getFloat("y"));
                Entity.Direction direction = Entity.Direction.fromString(resultSet.getString("direction"));
                String map = resultSet.getString("map");
                Player player = new Player(username, position, direction, map);
                addProfile(player);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteProfile(String username){
        if(getProfileIndex(username) == -1) return false;
        profiles.remove(getProfileIndex(username));
        if(preferences.contains("cloud_id")){
            String user_id = preferences.getString("cloud_id");

            try (Connection connection = databaseManager.getConnection()) {
                String query = "DELETE FROM characters WHERE username = ? and user_id = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setObject(2, UUID.fromString(user_id));
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                return false;
            }
        }
        saveProfiles();
        return true;
    }

    public Preferences getPreferences(){
        return preferences;
    }

}