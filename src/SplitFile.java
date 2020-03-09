import java.io.*;

public class SplitFile {// ghi file
    public static boolean splitFile(String source, String dest, int numberFile) throws FileNotFoundException, IOException {
        File sourceFile = new File(source);//Xác định đường dẫn tới file cần cắt
        if (sourceFile.exists() && sourceFile.isFile()) {//kiểm tra file nguồn tồn tại và nó có phải file k
            long sizeFile = sourceFile.length(); //lấy size của file nguồn
            long sizeSplitFile = (sizeFile / numberFile);
            //Mở luồng đọc file và ghi file . Nếu bằng với size cắt đk ở trên thì dừng và cắt tới file tiếp theo
            InputStream input = new FileInputStream(sourceFile);
            byte[] arr = new byte[1000];
            for (int i = 1; i <= numberFile; i++) {
                int j = 0;
                long a = 0;//biến đếm.ví dụ cắt xong đoạn đầu tiên nó sẽ lưu lại vào biến đếm. Sau đó tăng biến đếm lên và tiếp tục đọc để cắt
                OutputStream output = new FileOutputStream (dest + sourceFile.getName() + "." + i); //Nơi lưu file cắt
                System.out.println("File cat duoc la: " + sourceFile.getName() + "." + i);
                while ((j = input.read(arr)) != -1) {
                    output.write(arr, 0, j);
                    a += j;
                    if (a >= sizeSplitFile) {
                        break;
                    }
                }
                output.flush();
                output.close();
            }
            input.close();
            return true;

        } else {
            System.out.println("file khong ton tai");
            return false;
        }
    }
    public static boolean joinFile(String source) throws IOException {//Đọc file con ra ra để nối lại
        //lấy tên file khi chưa cắt
        String sourceFile = source.substring(0, source.lastIndexOf("."));//phương thức substring: trả về chuỗi mới là chuỗi con của chuỗi đã cho từ vị trí..
        File file = new File(sourceFile);
        //Tạo nơi lưu file mới.
        OutputStream output = new FileOutputStream(file);
        InputStream input;
        int count = 1;
        //Duyệt danh sách file dựa vào biến count.
        while (true) {
            String path = file + "." + count;
            File eachFile = new File(path);
            if (eachFile.exists()){
                input = new FileInputStream(eachFile);
                int i =0;
                byte[] arr = new byte[1000];
                while ((i = input.read(arr))!=-1){
                    output.write(arr, 0, i);
                }
                output.flush();
                input.close();
                count ++;
            }else
                System.out.println("File khong ton tai");;
                break;
        }
        output.close();
        return false;

    }

    public static void main(String[] args) throws IOException {
        splitFile("/Users/holoi/Desktop/Hon-Ca-Yeu-Duc-Phuc.mp3","/Users/holoi/Desktop",5);
        //splitFile("/Users/holoi/IdeaProjects/Cut File Mp3/src/Hon-Ca-Yeu-Duc-Phuc copy.mp3", "Hon-Ca-Yeu-Duc-Phuc",3);
       // joinFile("/Users/holoi/DesktopHon-Ca-Yeu-Duc-Phuc.mp3.1");

    }
}
