import { React } from "react";
import { useNavigate } from "react-router-dom";
import Header from "./Header";
import axios from "axios";
import toast, { Toaster } from "react-hot-toast";
import { Formik, Field, Form, ErrorMessage } from "formik";
import * as Yup from "yup";

export default function AddTodo() {

  /* EFFECTS */
  
  let navigate = useNavigate();

  const validationSchema = Yup.object().shape({
    title: Yup.string()
      .min(3, "Le titre doit contenir au minimum 3 caractères.")
      .max(60, "Le titre doit contenir au maximum 60 caractères")
      .required("Ce champs est obligatoire"),
    description: Yup.string(),
  });

  const initialValues = {
    title: "",
    state: "Todo",
    description: "",
  };

  const success = () => toast.success("Todo ajoutée avec succès !");

  const handleSubmit = (values) => {
    let URL = "http://localhost:8080/api/";
    axios
      .post(URL + "save", {
        title: values.title,
        description: values.description,
        state: values.state,
      })
      .then((response) => {
        console.log(response);
          navigate("/");
          success();
      });
  };

  /* VIEW */
  return (
    <>
      <Header />
      <Toaster />
      <h1 className="w-25 m-auto text-info text-center mb-5">
        Ajoute une todo à ta liste !
      </h1>
      <Formik
        initialValues={initialValues}
        validationSchema={validationSchema}
        onSubmit={(values) => handleSubmit(values)}
      >
        {({ resetForm }) => (
          <Form>
            <div className="form-group w-50 m-auto mt-5">
              <label htmlFor="title" className="h3">
                Titre <span className="text-danger">*</span>
              </label>
              <Field
                type="text"
                id="title"
                name="title"
                className="form-control form-control-lg h4"
              />
              <ErrorMessage
                name="title"
                component="small"
                className="text-danger h5"
              />
            </div>
            <div className="form-group w-50 m-auto mt-5">
              <label htmlFor="description" className="h3">
                Description
              </label>
              <Field
                as="textarea"
                type="text"
                id="description"
                name="description"
                className="form-control form-control-lg h4"
                rows={7}
                cols={5}
              />
              <ErrorMessage
                name="description"
                component="small"
                className="text-danger h5"
              />
            </div>
            <div className="w-50 m-auto mt-5">
              <button type="submit" className="btn btn-lg btn-info text-white">
                AJOUTER UNE TODO
              </button>
              <button
                type="button"
                onClick={resetForm}
                className="btn btn-lg btn-danger m-lg-5"
              >
                ANNULER
              </button>
            </div>
          </Form>
        )}
      </Formik>
    </>
  );
}
