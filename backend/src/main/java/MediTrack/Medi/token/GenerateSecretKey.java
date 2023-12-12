import java.security.SecureRandom;

public class GenerateSecretKey {
    public static void main(String[] args) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] sharedSecret = new byte[32]; // You can adjust the size as needed (e.g., 64 for HS512)
        secureRandom.nextBytes(sharedSecret);

        String base64SharedSecret = java.util.Base64.getEncoder().encodeToString(sharedSecret);
        System.out.println("Generated Secret Key (Base64): " + base64SharedSecret);
    }
}
