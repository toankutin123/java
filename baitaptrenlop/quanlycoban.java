import java.util.Scanner;

public class quanlycoban {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Quan ly diem sinh vien co ban ===");
        String maSV = readNonEmptyLine(scanner, "Nhap ma sinh vien:");
        String hoTen = readNonEmptyLine(scanner, "Nhap ho va ten:");
        double diemChuyenCan = readScore(scanner, "Nhap diem chuyen can (0-10):");
        double diemGiuaKy = readScore(scanner, "Nhap diem giua ky (0-10):");
        double diemCuoiKy = readScore(scanner, "Nhap diem cuoi ky (0-10):");

        double diemTongKet = tinhDiemTongKet(diemChuyenCan, diemGiuaKy, diemCuoiKy);
        String xepLoai = xepLoai(diemTongKet);

        System.out.println();
        System.out.println("Ket qua:");
        System.out.printf("Ma SV: %s\n", maSV);
        System.out.printf("Ho ten: %s\n", hoTen);
        System.out.printf("Diem chuyen can: %.2f\n", diemChuyenCan);
        System.out.printf("Diem giua ky: %.2f\n", diemGiuaKy);
        System.out.printf("Diem cuoi ky: %.2f\n", diemCuoiKy);
        System.out.printf("Diem tong ket: %.2f\n", diemTongKet);
        System.out.printf("Xep loai: %s\n", xepLoai);

        scanner.close();
    }

    private static String readNonEmptyLine(Scanner scanner, String prompt) {
        String value;
        do {
            System.out.print(prompt + " ");
            value = scanner.nextLine().trim();
            if (value.isEmpty()) {
                System.out.println("Gia tri khong duoc de trong. Vui long thu lai.");
            }
        } while (value.isEmpty());
        return value;
    }

    private static double readScore(Scanner scanner, String prompt) {
        double score;
        while (true) {
            System.out.print(prompt + " ");
            if (!scanner.hasNextDouble()) {
                System.out.println("Dinh dang khong hop le. Vui long nhap so thuc tu 0 den 10.");
                scanner.nextLine();
                continue;
            }
            score = scanner.nextDouble();
            scanner.nextLine();
            if (score < 0 || score > 10) {
                System.out.println("Diem phai nam trong khoang 0-10. Vui long nhap lai.");
            } else {
                break;
            }
        }
        return score;
    }

    private static double tinhDiemTongKet(double chuyenCan, double giuaKy, double cuoiKy) {
        return chuyenCan * 0.1 + giuaKy * 0.3 + cuoiKy * 0.6;
    }

    private static String xepLoai(double diemTongKet) {
        if (diemTongKet >= 8.5) {
            return "A";
        } else if (diemTongKet >= 7.0) {
            return "B";
        } else if (diemTongKet >= 5.5) {
            return "C";
        } else if (diemTongKet >= 4.0) {
            return "D";
        }
        return "F";
    }
}
