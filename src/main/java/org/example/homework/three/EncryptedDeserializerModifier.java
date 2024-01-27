package org.example.homework.three;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;

public class EncryptedDeserializerModifier extends BeanDeserializerModifier {
    public EncryptedDeserializerModifier(){}
    @Override
    public JsonDeserializer<?> modifyDeserializer(DeserializationConfig config, BeanDescription beanDesc, JsonDeserializer<?> deserializer) {
        return new EncryptedJsonDeserializer((JsonDeserializer<Object>) deserializer);
    }

}
