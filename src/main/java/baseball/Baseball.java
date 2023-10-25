package baseball;

import baseball.utils.RandomUtil;
import baseball.utils.UserInputUtil;

import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Baseball {

    public void startGame() {
        while(true) {
            startRound();
        }

    }

    private void startRound() {
        List<Integer> computerNumber = RandomUtil.generateRandomNumber(3);

        while(true) {
            int strike = 0;
            int ball = 0;

            System.out.println("숫자 야구 게임을 시작합니다.");
            System.out.print("숫자를 입력해주세요 : ");
            String userInput = readLine();
            if (userInput.length() != 3) {
                throw new IllegalArgumentException("잘못된 값을 입력했습니다. 애플리케이션을 종료합니다.");
            }
            for (char c : userInput.toCharArray()) {
                if (!Character.isDigit(c) || c == '0') {
                    throw new IllegalArgumentException("잘못된 값을 입력했습니다. 애플리케이션을 종료합니다.");
                }
            }

            Score score = new Score();
            List<Integer> userNumber = UserInputUtil.convertStringToIntegerList(userInput);
            for (int i = 0; i < 3; i++) {
                String result = compareNumber(computerNumber, userNumber.get(i), i);

                if (result.equals("스트라이크")) {
                    strike++;
                } else if (result.equals("볼")) {
                    ball++;
                }
            }

            score.setScore(ball, strike);
            printHint(ball, strike);

            if(score.getStrike() == 3) {
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                break;
            }
        }
    }

    private String compareNumber(List<Integer> computerNumber, int userInputValue, int index) {
        for(int i = 0; i < 3; i++) {
            if(computerNumber.contains(userInputValue) && i == index) {
                return "스트라이크";
            } else if(computerNumber.contains(userInputValue) && i != index) {
                return "볼";
            }
        }
        return "낫싱";
    }

    private void printHint(int ball, int strike) {
        if(strike == 0 && ball == 0) {
            System.out.print("낫싱");
        }

        if(ball > 0) {
            System.out.print(ball + "볼 ");
        }

        if(strike > 0) {
            System.out.print(strike + "스트라이크");
        }

        System.out.println();
    }
}
