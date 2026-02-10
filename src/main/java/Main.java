public class Main {
    public static void main(String[] args) {

        // 입력 담당 객체
        Input input = new Input();

        // 구입 금액 입력
        Price price = input.inputPrice();

        // 구입 금액을 기준으로 로또 발급
        LottoVendingMachine machine = new LottoVendingMachine();
        Lottos lottos = machine.genenrateLottos(price.getLottoCount());
        
        // 구매한 로또 출력
        Output.printLottos(lottos);

        // 당첨 번호 & 보너스 번호 입력
        WinningLotto winningLotto = input.inputWinningNumbers();

        // 구매 로또와 당첨 번호 match -> LottoResult에 기록됨
        LottoResult result = lottos.match(winningLotto);

        // 당첨 통계 및 수익률 출력
        Output.printLottoStatistics(result, price);
    }
}