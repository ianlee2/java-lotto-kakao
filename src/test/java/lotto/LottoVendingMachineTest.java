package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoVendingMachine;
import lotto.domain.Lottos;
import lotto.domain.Price;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LottoVendingMachineTest {
    @Test
    @DisplayName("가격에 해당하는 만큼의 로또를 반환한다.")
    void creates_lottos() {
        Price price = new Price("12000");
        LottoVendingMachine machine = new LottoVendingMachine();
        Lottos lottos = machine.genenrateAutoLottos(price.getLottoCount());
        assertThat(lottos.size()).isEqualTo(price.getLottoCount());
    }


    @Test
    @DisplayName("(수동) 로또 리스트를 전달하면 Lottos 객체로 반환")
    void returns_lottos() {
        // given
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(7, 8, 9, 10, 11, 12));
        List<Lotto> lottoList = List.of(lotto1, lotto2);

        // when
        LottoVendingMachine machine = new LottoVendingMachine();
        Lottos result = machine.generateManualLottos(lottoList);

        // then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(lottoList, result.getLottos());
    }
}
