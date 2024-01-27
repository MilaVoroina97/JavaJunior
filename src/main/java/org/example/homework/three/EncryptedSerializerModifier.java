package org.example.homework.three;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.introspect.BasicBeanDescription;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

import java.util.ArrayList;
import java.util.List;

public class EncryptedSerializerModifier extends BeanSerializerModifier {

    public EncryptedSerializerModifier() {
    }


    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {

        List<BeanPropertyWriter> newWriters = new ArrayList<>();
        for (BeanPropertyWriter writer : beanProperties) {
            if (null == writer.getAnnotation(Encrypt.class)) {
                newWriters.add(writer);
            } else {
                JsonSerializer<Object> serializer = new EncryptedJsonSerializer(writer.getSerializer());
                newWriters.add(writer);
            }
        }

        return newWriters;
    }
}
