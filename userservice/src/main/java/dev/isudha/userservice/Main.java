package dev.isudha.userservice;

import java.util.Date;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class Main {

    public static void main(String[] args) {
        // Create a JWT
        String token = JWT.create()
            .withIssuer("issuer")
            .withAudience("audience")
            .withSubject("subject")
            .withExpiresAt(new Date())
            .sign(Algorithm.HMAC256("secret"));

        // Create a JwtVerifier
        //        JwtVerifier verifier = JWT.verifier(token)
        //            .withIssuer("issuer")
        //            .withAudience("audience")
        //            .withSecret("secret");
        //
        //        // Verify the JWT
        //        boolean isValid = verifier.verify();

        // Print the result
        //        System.out.println(isValid);
    }
}
