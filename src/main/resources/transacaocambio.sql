CREATE TABLE `transacaocambio` (
   `id_transacaocambio` varchar(64) NOT NULL,
   `id_carteira` varchar(64) NOT NULL,
   `id_cotacao` varchar(64) not NULL,
   `dt_data` datetime not null,
   `nr_qtd` decimal(14,4) not NULL,
   `nrTipo` int not NULL,
   PRIMARY KEY (`id_transacaocambio`)
) ENGINE=InnoDB;
