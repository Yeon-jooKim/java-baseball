package baseball;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static baseball.Application.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FeatureListTest {

    @Test
    void 이미_만들어진_숫자가_아닌_난수_생성_검증() {
        List<Integer> list = List.of(1, 2);
        assertThat(GenerateNonDuplicateRandomNumber(list)).isNotIn(1, 2);
    }

    @Test
    void digits개의_중복되지_않은_난수_생성_검증() {
        Set<Integer> s = new HashSet<>(GenerateRandomNumber(3));
        assertThat(s.size()).isEqualTo(3);
    }

    @Test
    void 사용자입력의_크기가_3인지_확인() {
        String s = "345";
        assertThat(isValidLength(s.length())).isTrue();
        String s2 = "3454";
        assertThat(isValidLength(s2.length())).isFalse();
    }

    @Test
    void 사용자_입력_문자열을_Set으로_변환() {
        String s = "345";
        Set<Integer> set = Set.of(3, 4, 5);
        assertThat(isInputIsNonDuplicate(s)).isEqualTo(set);
    }

}
