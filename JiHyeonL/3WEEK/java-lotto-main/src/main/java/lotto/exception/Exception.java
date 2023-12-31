package lotto.exception;

import lotto.service.Info;

import java.util.Collections;
import java.util.List;

/**
 * 입력 예외처리 클래스
 */
public class Exception {
    /*
     * int로 변환 가능한지 체크
     */
    public void isCanConvertInt(String input) {
        isStringNumber(input);  // 숫자 체크
    }
    /*
     * List<String>이 List<Integer>로 변환 가능한지 체크
     */
    public void isConConvertIntList(List<String> input) {
        for (String str : input) {
            isStringNumber(str);    // 번호가 숫자인지 체크
        }
    }

    /*
     * 로또 구입 금액 예외처리 메소드를 모두 실행
     */
    public void isCostValid(int input) {
        isCostDividedUnit(input);   // 숫자이면 1000으로 나누어 떨어지는지 체크
    }
    /*
     * 당첨 번호 예외처리 메소드를 모두 실행
     */
    public void isAnswerValid(List<Integer> input) {
        isAnswerValidCountAndValidSplit(input);   // 번호가 6개인지 체크
        isNumbersNotSame(input);    // 번호 중 중복이 있는지 체크
        for (int number : input) {
            isNumberInRange(number);   // 번호 범위 체크
        }
    }
    /*
     * 보너스 번호 예외처리 메소드를 모두 실행
     */
    public void isBonusValid(int input) {
        isNumberInRange(input);  // 번호 범위 체크
    }

    /*
     * 문자열이 숫자인지 체크
     */
    private void isStringNumber(String str) {
        if(!str.matches("[0-9]+"))
            throw new IllegalArgumentException("[ERROR] 숫자가 아닌 번호가 존재합니다.");
    }
    /*
     * 로또 구입 금액이 1,000원으로 나누어 떨어지지 않는지 체크
     */
    private void isCostDividedUnit(int cost) {
        if(cost % Info.LOTTO_UNIT.getValue() != 0)
            throw new IllegalArgumentException("[ERROR] 로또 구입 금액이 " + Info.LOTTO_UNIT.getValue() + "로 나누어 떨어지지 않습니다.");
    }
    /*
     * 당첨 번호가 6개인지 체크
     */
    private void isAnswerValidCountAndValidSplit(List<Integer> numbers) {
        if (numbers.size() != Info.COUNT_NUMBERS.getValue())
            throw new IllegalArgumentException("[ERROR] 입력하신 로또 번호 개수가 " + Info.COUNT_NUMBERS.getValue() + "가 아닙니다.");
    }
    /*
     * 중복 숫자가 존재하는지 체크
     */
    private void isNumbersNotSame(List<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++)
            if (numbers.indexOf(numbers.get(i)) != numbers.lastIndexOf(numbers.get(i)))
                throw new IllegalArgumentException("[ERROR] 중복 번호가 존재합니다.");
    }
    /*
     * 숫자 범위가 1~45인지 체크
     */
    private void isNumberInRange(int number) {
        if (number < Info.START_NUMBER.getValue() || number > Info.END_NUMBER.getValue())
            throw new IllegalArgumentException("[ERROR] 입력하신 번호 중 " + Info.START_NUMBER.getValue() + " ~ " + Info.END_NUMBER.getValue() + " 범위가 아닌 숫자가 존재합니다.");
    }
    /*
     * 로또 당첨 번호와 보너스 번호가 다른지 체크
     */
    private void isNotSameAnswerAndBonus(List<Integer> answer, int bonus) {
        if (answer.contains(bonus))
            throw new IllegalArgumentException("[ERROR] 입력하신 보너스 번호와 같은 당첨 번호가 존재합니다.");
    }

}
