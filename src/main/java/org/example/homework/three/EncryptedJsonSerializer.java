package org.example.homework.three;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;


import java.io.IOException;
import java.io.StringWriter;

public class EncryptedJsonSerializer extends JsonSerializer<Object> {

    private final JsonSerializer<Object> serializer;

    public EncryptedJsonSerializer(JsonSerializer<Object> serializer) {
        this.serializer = serializer;
    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value != null) {
            StringWriter stringWriter = new StringWriter();
            JsonGenerator nestedGenerator = gen.getCodec().getFactory().createGenerator(stringWriter);

            if (serializer == null) {
                serializers.defaultSerializeValue(value, nestedGenerator);
            } else {
                serializer.serialize(value, nestedGenerator, serializers);
            }

            nestedGenerator.flush();
            nestedGenerator.close();

            String plaintextValue = stringWriter.toString();
            String encryptedValue = AESTools.encrypt(plaintextValue);
            gen.writeString(encryptedValue);
        }
    }
}
