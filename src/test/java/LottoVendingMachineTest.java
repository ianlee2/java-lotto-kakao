import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoVendingMachineTest {
    @Test
    @DisplayName("가격에 해당하는 만큼의 로또를 반환한다.")
    void creates_lottos() {
        Price price = new Price(12000);
        LottoVendingMachine machine = new LottoVendingMachine();
        Lottos lottos = machine.genenrateLottos(price.getLottoCount());
        assertThat(lottos.size()).isEqualTo(price.getLottoCount());
    }
}
