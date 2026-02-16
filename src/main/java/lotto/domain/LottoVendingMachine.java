package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoVendingMachine {

    public Lottos genenrateAutoLottos(Integer lottoCount) {
        LottoGenerator  lottoGenerator  = new AutoLottoGenerator();
        List<Lotto> generatedLottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            generatedLottos.add(lottoGenerator.generateLotto());
        }
        return new Lottos(generatedLottos);
    }

}
