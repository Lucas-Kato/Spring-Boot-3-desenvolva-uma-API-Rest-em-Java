ALTER TABLE medicos ADD COLUMN ativo boolean;
ALTER TABLE medicos
ALTER COLUMN ativo SET DEFAULT true;

