import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public final class PasswordHasher {

    public static byte[] passwordHash(String password){

        //  Generate salt
        //SecureRandom random = new SecureRandom();
        //byte[] salt = new byte[16];
        //random.nextBytes(salt);

        //  Or set salt static
        byte[] salt = "ItsMyFirstHash77".getBytes();

        // generate slow keyspec
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory;
        try{
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(keySpec).getEncoded();
            return hash;
        }catch (NoSuchAlgorithmException | InvalidKeySpecException e){
            e.printStackTrace();
            return null;
        }
    }
}
