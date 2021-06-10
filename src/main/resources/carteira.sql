CREATE TABLE `carteira` (
    `id_carteira` varchar(64) NOT NULL,
    `id_usuario` varchar(64) NULL,
    `vr_saldo` decimal(14,4) DEFAULT NULL,
    PRIMARY KEY (`id_carteira`)
) ENGINE=InnoDB;


