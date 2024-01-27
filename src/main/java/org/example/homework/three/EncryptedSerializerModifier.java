package org.example.homework.three;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
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
                BeanPropertyWriter newWriter = new CustomBeanPropertyWriter(writer, serializer);
                newWriters.add(newWriter);
            }
        }
        return newWriters;
    }

    static class CustomBeanPropertyWriter extends BeanPropertyWriter {

        private final JsonSerializer<Object> serializer;

        public CustomBeanPropertyWriter(BeanPropertyWriter base, JsonSerializer<Object> serializer) {
            super(base);
            this.serializer = serializer;
        }

        @Override
        public void serializeAsField(Object bean, JsonGenerator gen, SerializerProvider prov) throws Exception {
            // Use the custom serializer for serialization
            if (serializer != null) {
                serializeWithCustomSerializer(bean, gen, prov);
            } else {
                super.serializeAsField(bean, gen, prov);
            }
        }

        private void serializeWithCustomSerializer(Object bean, JsonGenerator gen, SerializerProvider prov) throws Exception {
            // Delegate serialization to the custom serializer
            if (serializer != null) {
                serializer.serialize(bean, gen, prov);
            } else {
                // If custom serializer is not available, serialize using default behavior
                super.serializeAsField(bean, gen, prov);
            }
        }
    }

}
