ALTER TABLE pacientes ADD COLUMN ativo boolean;
UPDATE pacientes SET ativo = true;
ALTER TABLE pacientes ALTER COLUMN ativo SET NOT NULL;