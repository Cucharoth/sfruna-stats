
INSERT INTO public.usuario (usuario_id, nombre_usuario, password_usuario, role) 
VALUES
(1, 'Cucharoth','$2a$10$ugBH4mQJVitDDj3tohQ8RO5gsXCLPVufBPEvvuywRYvTA50nkVO7G', 'ADMIN'),
(2, 'Belen','$2a$10$pLxXxWwFtdt087OfqglnbuVWZqYG/ddWkEAUt4Up6G5UJLkr45Kwi', 'ADMIN')
ON CONFLICT DO NOTHING;
