package baseball;


import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;

public class Application {


    public static List<Integer> GenerateRandomNumber(int digits) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < digits; i++) {
            list.add(GenerateNonDuplicateRandomNumber(list));
        }
        return list;
    }

    public static boolean isValidLength(int n) {
        return n == 3;
    }

    public static Set<Integer> InputToSet(String userInput) {
        String[] splitUserInput = userInput.split("");
        Set<Integer> set =new LinkedHashSet<>();
        for (int i = 0; i <userInput.length() ; i++) {
            set.add(Integer.parseInt(splitUserInput[i]));
        }
        return set;
    }

    public static boolean isValidSetSize(Set set) {
        return set.size() == 3;
    }


    public static int GenerateNonDuplicateRandomNumber(List<Integer> randNum) {
        int k;
        do {
            k = Randoms.pickNumberInRange(1, 9);
        }
        while (randNum.contains(k));
        return k;
    }

    public static boolean isContain(int number, List<Integer> answer) {
        return answer.contains(number);
    }

    public static boolean isStrike(int number, int answerNumber) {
        return number == answerNumber;
    }



    static class Nums{
        public Integer[] inputNums;
        Nums(String inputData) throws Exception  {
            if(!isValidLength(inputData.length())) throw new IllegalArgumentException() ;
            Set<Integer> sets = InputToSet(inputData);
            if(!isValidSetSize(sets)) throw new IllegalArgumentException();
            this.inputNums = sets.toArray(new Integer[3]);
        }
    }



    public static void main(String[] args) {

        // TODO: 프로그램 구현
    }
}
