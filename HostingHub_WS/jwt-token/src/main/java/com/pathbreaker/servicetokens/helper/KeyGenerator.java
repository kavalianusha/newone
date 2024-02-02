package com.pathbreaker.servicetokens.helper;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.security.*;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.Objects;

public class KeyGenerator {

    public static void main(String[] args) throws Exception{
            // Read keystore from resource folder
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URL resource = classLoader.getResource("certificates/authtoken.jks");
            System.out.println(resource);
            File file = new File(Objects.requireNonNull(resource).toURI());
            char[] keyPass = "pathbreaker".toCharArray();
            String alias = "example";

            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            try (FileInputStream is = new FileInputStream(file)) {
                keystore.load(is, keyPass);
            }
            System.out.println("the keystore "+keystore);
            Key key = keystore.getKey(alias, keyPass);
            System.out.println("key "+key);
            if (key instanceof PrivateKey) {
                System.out.println("this is key generator");

                // Get certificate of public key
                Certificate cert = keystore.getCertificate(alias);
                // Get public key

                PublicKey publicKey = cert.getPublicKey();
                RSAPublicKey rp = (RSAPublicKey)publicKey;
                System.out.println("the rs "+rp.getPublicExponent().toString());

                System.out.println("this is the public key :"+Base64.getEncoder().encodeToString(publicKey.getEncoded()));

                PrivateKey privateKey = (PrivateKey) key;
                RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;
                System.out.println("the rs " + rsaPrivateKey.getPrivateExponent().toString());

                System.out.println("this is the private key: " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));
                //return new KeyPair(publicKey, (PrivateKey) key);
            }
        }
}
