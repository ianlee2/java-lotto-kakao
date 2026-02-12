package lotto;

import lotto.domain.AutoLottoGenerator;
import lotto.domain.Lotto;
import lotto.domain.LottoGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoLottoGeneratorTest {

    @Test
    public void 서로_다른_6자리_로또번호_생성() {
        LottoGenerator autoLottoGenerator = new AutoLottoGenerator();
        Lotto lotto = autoLottoGenerator.generateLotto();

        assertThat(lotto).isNotNull();
    }
}
