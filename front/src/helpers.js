const validateList = (list) => {
  let validation = {
    message: "",
    validate: true,
  };
  if (list.length <= 3) {
    validation.message = "La Lista no puede ser menor a 3 caracteres";
    validation.validate = false;
  }

  if (list.length === 0) {
    validation.message = "La Lista no puede estar vacia";
    validation.validate = false;
  }
  return validation;
};

export { validateList };
