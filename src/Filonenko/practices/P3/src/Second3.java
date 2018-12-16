public class Second3 {
    int result(int[] db) {
        int sum = 0;
        for(int i=0;i<db.length;i++) {
            sum += db[i];
        }
        int totalSum = 0;
        for(int i=0;i<=db.length;i++) {
           totalSum += i;
        }
        return totalSum - sum;
    }
}
// Берем сумму всех чисел и отнимаем от нее сумму всех чисел в массиве. Получаем недостающее число.