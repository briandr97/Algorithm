import java.util.Scanner;
import java.io.FileInputStream;
 
class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
 
        for(int test_case = 1; test_case <= T; test_case++)
        {
            // 입력
            int n = sc.nextInt();
            int[][] arr = new int[n][n];
            StringBuilder[] sbs = new StringBuilder[n];
            for(int i=0; i<n; i++) {
                sbs[i] = new StringBuilder();
                for(int j=0; j<n; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }
             
            // 회전
            for(int r=0; r<3; r++) {
                arr = rotate(arr, n);
                for(int i=0; i<n; i++) {
                    for(int j=0; j<n; j++) {
                        sbs[i].append(arr[i][j]);
                    }
                    if(r < 2) sbs[i].append(" ");
                }
            }
 
            System.out.println("#" + test_case);
            for(int i=0; i<n; i++) {
                System.out.println(sbs[i].toString());
            }
        }
    }
     
    private static int[][] rotate(int[][] arr, int n) {
        int[][] copied = new int[n][n];
        for(int row=0; row<n; row++) {
            for(int column=0; column<n; column++) {
                copied[column][n - 1 - row] = arr[row][column];
            }
        }
        return copied;
    }
}