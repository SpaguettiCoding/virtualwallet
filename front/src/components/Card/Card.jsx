import React from 'react'
import s from './Card.module.css'
import Cards from 'react-credit-cards-2';
import { useState } from 'react';
import 'react-credit-cards-2/dist/es/styles-compiled.css';



const Card = () => {

  const [state, setState] = useState({
    number: '',
    expiry: '',
    cvc: '',
    name: '',
    focus: '',
  });

  const handleInputChange = (evt) => {
    const { name, value } = evt.target;
    
    setState((prev) => ({ ...prev, [name]: value }));
  }

  const handleInputFocus = (evt) => {
    setState((prev) => ({ ...prev, focus: evt.target.name }));
  }

  return (
    <div>
      <Cards
        number={state.number}
        expiry={state.expiry}
        cvc={state.cvc}
        name={state.name}
        focused={state.focus}
      />
      <form className={s.Form}>
      <input
         name="name"
         placeholder="Full Name"
         value={state.name}
         onChange={handleInputChange}
         onFocus={handleInputFocus}
         className={s.Input}
       />
        <input
          name="number"
          placeholder="Card Number"
          value={state.number}
          onChange={handleInputChange}
          onFocus={handleInputFocus}
          className={s.Input}
        />
        <input
         name="expiry"
         placeholder="Expiry"
         value={state.expiry}
         onChange={handleInputChange}
         onFocus={handleInputFocus}
         className={s.Input}
       />
        <input
         name="cvc"
         placeholder="cvc"
         value={state.cvc}
         onChange={handleInputChange}
         onFocus={handleInputFocus}
         className={s.Input}
       />

       <button className={s.Btton}>Submit</button>
      </form>
    </div>
  );



}

export default Card