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
}
