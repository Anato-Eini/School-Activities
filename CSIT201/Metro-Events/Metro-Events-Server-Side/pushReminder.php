<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
if(isset($_POST['username'], $_POST['message'])){
    $userJSON = json_decode(file_get_contents('user.json'), true);
    foreach($userJSON as &$user){
        if($user['username'] == $_POST['username']){
            $user['reminders'][] = [
                "eventName" => $_POST['message']
            ];
            file_put_contents('user.json', json_encode($userJSON, JSON_PRETTY_PRINT));
            echo json_encode(array(
                "success" => true,
                "message" => "Reminder has sent to the user"
            ));
            exit;
        }
    }
    echo json_encode(array(
        "success" => false,
        "message" => "User does not exist"
    ));
}else{
    echo json_encode(array(
        "success"=>false,
        "message"=> "Parameters not provided"
    ));
}
/*
 * url: http://hyeumine.com/DL0wgqiJ/Acabal/prefinalexam/MetroEvents/pushReminder.php
 * method: 'POST'
 * parameters: {username, message}
 */