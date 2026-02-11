import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoLottoGeneratorTest {

    @Test
    public void 서로_다른_6자리_로또번호_생성() {
        LottoGenerator autoLottoGenerator = new AutoLottoGenerator();
        Lotto lotto = autoLottoGenerator.generateLotto();

        assertThat(lotto).isNotNull();
    }
}
