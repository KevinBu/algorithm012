package cn.com.kevin.algorithm.greedy;

/**
 * LeeCode 860
 * 柠檬水找零
 *
 * 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。
 *
 * 顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
 *
 * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
 *
 * 注意，一开始你手头没有任何零钱。
 *
 * 如果你能给每位顾客正确找零，返回 true ，否则返回 false。
 *
 * 示例 1：
 *
 * 输入：[5,5,5,10,20]
 * 输出：true
 * 解释：
 * 前 3 位顾客那里，我们按顺序收取 3 张 5 美元的钞票。
 * 第 4 位顾客那里，我们收取一张 10 美元的钞票，并返还 5 美元。
 * 第 5 位顾客那里，我们找还一张 10 美元的钞票和一张 5 美元的钞票。
 * 由于所有客户都得到了正确的找零，所以我们输出 true。
 * 示例 2：
 *
 * 输入：[5,5,10]
 * 输出：true
 * 示例 3：
 *
 * 输入：[10,10]
 * 输出：false
 * 示例 4：
 *
 * 输入：[5,5,10,10,20]
 * 输出：false
 * 解释：
 * 前 2 位顾客那里，我们按顺序收取 2 张 5 美元的钞票。
 * 对于接下来的 2 位顾客，我们收取一张 10 美元的钞票，然后返还 5 美元。
 * 对于最后一位顾客，我们无法退回 15 美元，因为我们现在只有两张 10 美元的钞票。
 * 由于不是每位顾客都得到了正确的找零，所以答案是 false。
 *
 */
public class LemonadeChange {
    /**
     * 柠檬水找零
     * 使用贪心算法
     * @return boolean 返回布尔值
     */
    public static boolean lemonadeChange(int [] bills){
        int five = 0; //拥有5元钱的数量
        int ten = 0; //拥有10元钱的数量
        for(int bill : bills){
            if(bill == 5){// 顾客给了5元钱，则不用找零,并且记录拥有5元的数量加1
                five++;
            }else if(bill == 10){// 顾客给了10元，则需要找5元零钱
                if(five == 0){
                    // 如果没有5元钱，则说明找不开，返回false
                    return false;
                }else{
                    ten++; //拥有10元的数量加
                    five--; // 因为找零，因此拥有5元的数量减1
                }
            }else{
                // 根据题意，此处只能是20元
                // 根据贪心算法，找零时尽量先用大额的即先用10元去找零，如果没有则用5元找零
                // 首先判断所拥有的零钱是否足以支付找零
                if(ten > 0 && five > 0){
                    ten--;
                    five--;
                }else if(five >= 3){
                    five -= 3;
                }else {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int [] bills = {5,5,5,10,20};
        System.out.println(lemonadeChange(bills));
    }
}
