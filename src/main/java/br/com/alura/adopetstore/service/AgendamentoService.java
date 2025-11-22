package br.com.alura.adopetstore.service;

import br.com.alura.adopetstore.email.EmailRelatorioGerado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {

    @Autowired
    private RelatorioService relatorioService;
    @Autowired
    private EmailRelatorioGerado emailRelatorioGerado;

    @Scheduled(cron = "0 0 6 * * *")
    public void envioEmailsAgendados() {
        var estoqueZerado = relatorioService.infoEstoque();
        var faturamentoObtido = relatorioService.faturamentoObtido();
        emailRelatorioGerado.enviar(estoqueZerado, faturamentoObtido);
        System.out.println(estoqueZerado);
        System.out.println(faturamentoObtido);
        // Informa a thread usada no agendamento, que é diferente da principal
        System.out.println("Thread do agendador: " + Thread.currentThread().getName());
    }
}
