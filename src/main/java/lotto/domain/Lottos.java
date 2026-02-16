package lotto.domain;

import java.util.ArrayList;
import java.util.List;

// 동일 회차 기준 구매한 로또 번호들
public class Lottos {
    private List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public int size() {
        return lottos.size();
    }

    // 당첨 로또와 비교하여 전체 결과 집계
    public LottoResult match(WinningLotto winningLotto) {
        LottoResult result = new LottoResult();

        for (Lotto lotto : lottos) {
            int matchCount = lotto.matchNumbers(winningLotto);
            boolean bonusMatch = lotto.matchBonus(winningLotto);

            result.addResult(matchCount, bonusMatch);
        }

        return result;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public Lottos merge(Lottos other) {
        List<Lotto> merged = new ArrayList<>(this.lottos);
        merged.addAll(other.getLottos());
        return new Lottos(merged);
    }
}
