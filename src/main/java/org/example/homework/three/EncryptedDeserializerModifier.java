package org.example.homework.three;


import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;

public class EncryptedDeserializerModifier extends BeanDeserializerModifier {
    public EncryptedDeserializerModifier() {
    }

    @Override
    public JsonDeserializer<?> modifyDeserializer(DeserializationConfig config, BeanDescription beanDesc, JsonDeserializer<?> deserializer) {
        if (beanDesc.getBeanClass().equals(Student.class)) {
            return new EncryptedJsonDeserializer((JsonDeserializer<Object>) deserializer);
        }
        return deserializer;
    }
}


