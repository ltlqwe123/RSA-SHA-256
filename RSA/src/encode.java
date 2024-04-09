import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;
import java.util.Scanner;

public class encode {
    public static void main(String[] args) {
        new encode();
    }
    public encode(){
        Scanner input = new Scanner(System.in);
        System.out.print("Nhập vào đoạn văn bản cần mã hóa: ");
        String text = input.nextLine();
        maHoa_giaiMa_RSA(text);
    }
    public void maHoa_giaiMa_RSA(String src){
        try{
            // Khởi tạo 1 đối tượng sử dụng mã hóa RSA để tạo cặp khóa RSA
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

            // Xác định kích thước cho cặp khóa, mỗi khóa là 2482 bit
            keyPairGenerator.initialize(2048);

            // Tạo một cặp khóa mới gồm: public và private key bằng generateKeyPair() của keyPairGenerator
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            // Nhận khóa công khai từ keyPair bằng getPublic()
            PublicKey publicKey = keyPair.getPublic();

            // Nhận khóa riêng tư từ keyPair bằng getPrivate()
            PrivateKey privateKey = keyPair.getPrivate();

            // Khởi tạo một đối tượng Cipher với thuật toán RSA
            Cipher cipher = Cipher.getInstance("RSA");

            // Đặt chế độ hoạt động của cipher là ENCRYPT_MODE để mã hóa dữ liệu
            // Sử dụng khóa công khai để mã hóa dữ liệu
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            // Trả về văn bản sau khi mã hóa với RSA bằng khóa công khai
            byte[] textCrypt = cipher.doFinal(src.getBytes());

            // Chuyển từ byte[] sang base64
            String textAfter = Base64.getEncoder().encodeToString(textCrypt);

            System.out.println("Chuỗi sau khi mã hóa: " + textAfter);

            // -------------------------------------------------------------------------------

            // Đặt chế độ giải mã với khóa riêng tư
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            // Thực hiện giải mã
            byte[] textDeCrypt = cipher.doFinal(textCrypt);

            // Chuyển từ byte array sang chuỗi
            String textOrigin = new String(textDeCrypt);

            System.out.println("Chuỗi sau khi giải mã: " + textOrigin);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}