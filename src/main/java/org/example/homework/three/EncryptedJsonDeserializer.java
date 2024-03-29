package org.example.homework.three;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class EncryptedJsonDeserializer extends JsonDeserializer<Object> {

    private JsonDeserializer<Object> deserializer;

    public EncryptedJsonDeserializer(JsonDeserializer<Object> deserializer) {
        this.deserializer = deserializer;
    }

    public EncryptedJsonDeserializer(){}

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String encryptedValue = jsonParser.getValueAsString();
        String decryptedValue = AESTools.decrypt(encryptedValue);

        if (deserializer == null) {
            return Double.parseDouble(decryptedValue); // If deserializer is null, return the decrypted string directly
        }

        JsonParser nestedParser = jsonParser.getCodec().getFactory().createParser(decryptedValue);
        nestedParser.nextToken();
        return deserializer.deserialize(nestedParser, deserializationContext);
    }
}

