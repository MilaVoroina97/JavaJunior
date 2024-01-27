package org.example.homework.three;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class EncryptionModule extends Module {

    public final static String ARTIFACT_ID = "jackson-hb-encryption";
    public final static Version VERSION = new Version(1, 0, 0, null);

    public EncryptionModule(){

    }

    @Override
    public String getModuleName() {
        return ARTIFACT_ID;
    }

    @Override
    public Version version() {
        return VERSION;
    }

    @Override
    public void setupModule(SetupContext setupContext) {
        setupContext.addBeanSerializerModifier(new EncryptedSerializerModifier());
        setupContext.addBeanDeserializerModifier(new EncryptedDeserializerModifier());
    }



    public static ObjectMapper createMapper(){
        ObjectMapper objectMapper  = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.registerModule(new EncryptionModule());
        return objectMapper;
    }

}
