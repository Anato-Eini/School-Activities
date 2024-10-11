package com.example.client.Classes;


import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.sql.Timestamp;

public class TimestampTypeAdapter extends TypeAdapter<Timestamp> {

    @Override
    public void write(JsonWriter out, Timestamp value) throws IOException {
        out.value(value.toString());
    }

    @Override
    public Timestamp read(JsonReader in) throws IOException {
        return Timestamp.valueOf(in.nextString());
    }
}