# exemplosJavaRMI

##Java RMI - Implementação de uma calculadora simples.

###Tutorial para executar os projetos:

projeto: **java-rmi-remote-base**
- Representa a Interface Remota da arquitetura Java RMI.
- Exportar como uma library java (.jar).

projeto: **java-rmi-server**
- Representa o Servidor da arquitetura Java RMI.
- Importar no classpath a library do projeto **java-rmi-remote-base**.

projeto: **java-rmi-client**
- Representa o Cliente da arquitetura Java RMI.
- Importar no classpath a library do projeto **java-rmi-remote-base**.

Executar os projetos:
- Executar o servidor de nomes da arquitetura Java RMI, rmiregistry 9876 (porta opcional, no entanto, deve ser a mesma definida no Servidor e Cliente -  **LocateRegistry.getRegistry** ) dentro da pasta /bin (compilados), do projeto **java-rmi-remote-base** (Interface Remota).
- Executar Servidor (método main).
- Executar Cliente  (método main).

**Obs: **Para executar os projetos em computadores diferentes, alterar o host (IP) e port (porta) do **LocateRegistry.getRegistry** do Servidor e Cliente.

