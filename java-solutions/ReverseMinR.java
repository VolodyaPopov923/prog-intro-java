import java.util.Arrays;

public class ReverseMinR {
    private static Integer[] append(Integer[] arg, Integer element) {
        Integer[] array = Arrays.copyOf(arg, arg.length * 2);
        array[arg.length] = element;
        return array;
    }
    private static Integer[][] add(Integer[][] arg, Integer[] element) {
        Integer[][] array = Arrays.copyOf(arg, arg.length * 2);
        array[arg.length] = element;
        return array;
    }
    public static void main (String[] args) {
        Integer[][] data = new Integer[1][];
        Integer[] data2 = new Integer[1];
        Scanner sc = new Scanner(System.in);
        int cnt2 = 0;
        while(sc.hasNextLine()) {
            int cnt = 0;
            Scanner s2 = new Scanner(sc.nextLine());
            while(s2.hasNextInt()){
                if (data2.length == cnt){
                    data2 = append(data2, s2.nextInt());
                }else{
                    data2[cnt] = s2.nextInt();
                }
                cnt++;
            }
            if(data2.length == 0){
                if (data.length == cnt2){
                    data = add(data, null);
                }else{
                    data[cnt2] = null;
                }
            }else{
                Integer[] new_data2 = Arrays.copyOf(data2, cnt);
                if (data.length == cnt2){
                    data = add(data, new_data2);
                }else{
                    data[cnt2] = new_data2;
                }
            }
            cnt2++;
            data2 = new Integer[1];
        }
        sc.close();
    Integer[][] new_data = Arrays.copyOf(data, cnt2);
    for (int i = 0;  i < new_data.length; i++){
        int min_c = Integer.MAX_VALUE;
        if (new_data[i] != null){
            for (int j = 0;  j < new_data[i].length; j++){
                min_c = Math.min(new_data[i][j], min_c);
                System.out.print(min_c);
                if (j != new_data[i].length){
                    System.out.print(" ");
                }
            }
        }
        System.out.println();
    }
    }
}