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

    public LottoResult match(WinningLotto winningLotto) {
        LottoResult result = new LottoResult();

        for (Lotto lotto : lottos) {
            boolean bonusMatch = lotto.getLottoNumbers().contains(winningLotto.getBonusNumber());

            int matchCount = 0;
            for (LottoNumber lottoNumber : lotto.getLottoNumbers()) {
                if (winningLotto.getLottoNumbers().contains(lottoNumber)) matchCount++;
            }

            result.addResult(matchCount, bonusMatch);
        }

        return result;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
