import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoLottoGeneratorTest {

    @Test
    public void 서로_다른_6자리_로또번호_생성() {
        AutoLottoGenerator autoLottoGenerator = new AutoLottoGenerator();
        Lotto lotto = autoLottoGenerator.generateLotto();

        assertThat(lotto).isNotNull();
    }
}
