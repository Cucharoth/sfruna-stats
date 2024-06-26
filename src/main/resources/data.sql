
INSERT INTO stats.imagen_perfil (imagen_id, link)
VALUES
(1, 'https://imgur.com/UZSNN9v.png')
ON CONFLICT DO NOTHING;

INSERT INTO stats.usuario (usuario_id, nombre_usuario, password_usuario, role, imagen_perfil_id) 
VALUES
(1, 'Cucharoth','$2a$10$ugBH4mQJVitDDj3tohQ8RO5gsXCLPVufBPEvvuywRYvTA50nkVO7G', 'ADMIN',1),
(2, 'Belen','$2a$10$pLxXxWwFtdt087OfqglnbuVWZqYG/ddWkEAUt4Up6G5UJLkr45Kwi', 'ADMIN',1),
(3, 'Caramelo', '$2a$10$ugBH4mQJVitDDj3tohQ8RO5gsXCLPVufBPEvvuywRYvTA50nkVO7G', 'USER',1),
(4, 'Oscure', '$2a$10$ugBH4mQJVitDDj3tohQ8RO5gsXCLPVufBPEvvuywRYvTA50nkVO7G','USER',1),
(5, 'Neme', '$2a$10$ugBH4mQJVitDDj3tohQ8RO5gsXCLPVufBPEvvuywRYvTA50nkVO7G','USER',1)
ON CONFLICT DO NOTHING;

INSERT INTO stats.juego (juego_id, nombre_juego)
VALUES
(1,	'Golden Sun'),
(2,	'Kirby'),
(3,	'WoW'),
(4, 'Diablo')
ON CONFLICT DO NOTHING;

INSERT INTO stats.logro (logro_id, nombre_logro, descripcion, juego_id, puntos_logro)
VALUES
(8,	    '¡Login!',	    'Ingresa por primera vez',	    1,	10),
(9,	    '¡Compra Juego!',	'Compra tu primer juego',	        1,	50),
(19,	'¡Lee Noticia!',  'Lee tu primer noticia',	            1,	10),
(20,	'¡Juega Juego!',	    'Juega tu primer Juego',	    1,	10),
(21,	'mi-logro',	    'este es mi logro',	            1,	10),
(22,	'mi-logro',	    'este es mi logro',	            1,	10)
ON CONFLICT DO NOTHING;

INSERT INTO stats.noticia (noticia_id, titulo_noticia, content_noticia)
VALUES
(1, 'Chris Metzen returns to build WoWs next generation of adventures','Before he retired from Blizzard in 2016, Metzen worked on every Warcraft game, helping to build the story and lore. He eventually became Blizzard'' s senior vice president of story
and franchise development
and was involved in all of the studio ''s projects'),
(2,	'Datatmined Mythic+ Tuning 10.2 PTR - Black Rook Hold Buffs', 'In yesterday''s 10.2 PTR build,
Black Rook Hold got some buffs on Mythic + difficulty.')
ON CONFLICT DO NOTHING;

INSERT INTO stats.usuario_adquiere_juego (juego_id, usuario_id, fecha)
VALUES
(2,	2,	'2023-10-09'),
(1,	2,	'2023-03-09'),
(1,	1,	'2023-10-30'),
(3,	1,	'2023-11-23'),
(2,	1,	'2023-12-02'),
(4, 4, '2023-12-02'),
(3, 5, '2023-11-02'),
(3, 3, '2023-12-01')
ON CONFLICT DO NOTHING;

INSERT INTO stats.usuario_has_logro (logro_id, usuario_id, completado, fecha_completado)
VALUES
(9,	1,	't',	'2023-10-30 01:59:30.178732'),
(8,1,	't',	'2023-11-04 05:06:09'),
(8,	2,	't',	'2023-11-04 05:05:54'),
(22,3,'t','2023-12-11 05:05:54'),
(21,3,'t','2023-11-07 05:05:54'),
(9,4,'t','2023-11-07 05:05:54'),
(21,5,'t','2023-11-07 05:05:54'),
(19, 1, 't', '2023-12-13 05:05:54'),
(20, 1, 't', '2023-12-05 05:05:54')
ON CONFLICT DO NOTHING;

INSERT INTO stats.usuario_juega_juego (usuario_id, juego_id, tiempo_inicio, tiempo_jugado)
VALUES
(1,	1,	'2023-10-30 01:59:36.62287',	'00:00:15.825606'),
(1,	2,	'2023-10-09 15:06:33.062699',	'00:00:45.916429'),
(2,	1,	'2023-10-09 15:05:30.447555',	'00:02:39.968647'),
(3,	3,	'2023-10-09 15:05:30.447555',	'00:10:39.968647'),
(4,	4,	'2023-10-09 15:05:30.447555',	'00:30:40.968647'),
(5,	3,	'2023-10-09 15:05:30.447555',	'00:20:50.968647')
ON CONFLICT DO NOTHING;

INSERT INTO stats.usuario_lee_noticia (usuario_id, noticia_id, fecha)
VALUES
(2,	1,	'2023-10-23 03:15:15.951944'),
(1,	2,	'2023-10-23 23:20:33'),
(1,	1,	'2023-11-03 22:44:55.367443'),
(3,	1,	'2023-12-03 22:44:55.367443'),
(4,	2,	'2023-12-04 22:44:55.367443')
ON CONFLICT DO NOTHING;
