package baseball;


import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;

public class Application {


    public static List<Integer> generateRandomNumber(int digits) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < digits; i++) {
            list.add(generateNonDuplicateRandomNumber(list));
        }
        return list;
    }


    public static Set<Integer> inputToSet(String userInput) {
        String[] splitUserInput = userInput.split("");
        Set<Integer> set = new LinkedHashSet<>();
        for (int i = 0; i < userInput.length(); i++) {
            set.add(Integer.parseInt(splitUserInput[i]));
        }
        return set;
    }

    public static boolean isValidSetSize(Set set) {
        return set.size() == 3;
    }


    public static int generateNonDuplicateRandomNumber(List<Integer> randNum) {
        int k;
        do {
            k = Randoms.pickNumberInRange(1, 9);
        }
        while (randNum.contains(k));
        return k;
    }

    public static boolean isAnswerContainNumber(int number, List<Integer> answer) {
        return answer.contains(number);
    }

    public static boolean isStrike(int number, int answerNumber) {
        return number == answerNumber;
    }

    public static int[] judgment(Integer[] input, List<Integer> answer) {
        int[] result = new int[]{0, 0}; //result[0] = 볼, result[1] = 스트라이크
        for (int i = 0; i < 3; i++) {
            int[] temp = returnJudgmentResult(input[i], i, answer);
            result[0] += temp[0];
            result[1] += temp[1];
        }
        return result;
    }

    public static int[] returnJudgmentResult(int input, int index, List<Integer> answer) {
        int[] result = new int[]{0, 0};
        if (isStrike(input, answer.get(index))) {
            result[1]++;
            return result;
        }
        if (isAnswerContainNumber(input, answer)) {
            result[0]++;
        }
        return result;
    }


    static class Nums {
        public Integer[] inputNums;

        Nums(String inputData) {
            if (!isNotNumber(inputData)) throw new IllegalArgumentException("숫자가 아닌 문자 포함");
            Set<Integer> sets = inputToSet(inputData);
            if (!isValidSetSize(sets)) throw new IllegalArgumentException("잘못된 숫자 조합");
            this.inputNums = sets.toArray(new Integer[3]);
        }
    }

    public static boolean isNotNumber(String input) {
        for (int i = 0; i < input.length(); i++) {
            char tempCharacter = input.charAt(i);
            if (!(tempCharacter >= '0' && tempCharacter <= '9')) return false;
        }
        return true;
    }


    public static void baseballGame() {
        List<Integer> answerList = generateRandomNumber(3);
        int[] judg = new int[]{0, 0};
        while (judg[1] != 3) {
            judg = getJudgArray(answerList);
        }
    }

    private static int[] getJudgArray(List<Integer> answerList) {
        int[] judg;
        System.out.print("숫자를 입력해주세요 : ");
        try {
            Nums nums = new Nums(Console.readLine());
            judg = judgment(nums.inputNums, answerList);
            System.out.println(printJudge(judg));
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return judg;
    }

    public static String printJudge(int[] judg) {
        String result = "";
        if (judg[0] != 0) {
            result += judg[0] + "볼";
        }
        if (judg[0] != 0 && judg[1] != 0) {
            result += " ";
        }
        if (judg[1] != 0) {
            result += judg[1] + "스트라이크";
        }
        if (result.equals("")) {
            return "낫싱";
        }
        return result;
    }

    public static void main(String[] args) {
        int endGameValue = 0;
        System.out.println("숫자 야구 게임을 시작합니다.");
        while (endGameValue != 2) {
            try {
                baseballGame();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                throw e;
            }
            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            endGameValue = Integer.parseInt(Console.readLine());
        }
    }

}
