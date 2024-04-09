import java.security.MessageDigest;
import java.util.Scanner;

public class view {
    public static void main(String[] args) {
        new  view();
    }
    public view() {
        Scanner input = new Scanner(System.in);
        System.out.print("Nhập văn bản cần mã hóa: ");
        String src = input.nextLine();
        System.out.println("Chuỗi sau khi mã hóa: " + maHoa(src));
    }

    public StringBuffer maHoa(String src){
        StringBuffer text = null;

        try{
            // Khởi tạo đối tượng với mã hóa SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Thực thi mã hóa với tham số src
            byte[] encodedhash = digest.digest(src.getBytes());

            // Chuyển đổi byte array thành chuỗi hex
            text = new StringBuffer();
            for (int i=0; i<encodedhash.length; i++){
                // Với mỗi phần tử trong byte array chuyển đổi từ String thành hex với Off, sử dụng toán tử AND bit
                String hex = Integer.toHexString(0xff & encodedhash[i]);
                // Nếu
                if (hex.length() == 1) {
                    text.append('0');
                }
                text.append(hex);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return text;
    }
}
