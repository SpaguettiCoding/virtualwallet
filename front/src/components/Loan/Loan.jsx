import React, { useState } from 'react'
import s from './Loan.module.css'


const Loan = () => {
  const [amm, setAmm] = useState(0)
  const [total7Days, setTotal7Days] = useState('');
  const [total14Days, setTotal14Days] = useState('');
  const [total30Days, setTotal30Days] = useState('');
  let currentDate = new Date();
  const futureDates = [];

  for (let i = 0; i < 5; i++) {
    futureDates.push(currentDate.toLocaleDateString('en-US'));
    currentDate.setDate(currentDate.getDate() + (i === 0 ? 7 : i === 1 ? 7 : i === 2 ? 16 : 15));
  }


  const handleInput = (e) => {
      const amount = e.target.value

      if (/^\d*\.?\d*$/.test(amm)) {
        setAmm(amount);
  
        const loan7Days = parseFloat(amount) + parseFloat(amount) * 0.03;
        const loan14Days = parseFloat(amount) + parseFloat(amount) * 0.05;
        const loan30Days = parseFloat(amount) + parseFloat(amount) * 0.07;
  
        setTotal7Days(loan7Days.toFixed(2));
        setTotal14Days(loan14Days.toFixed(2));
        setTotal30Days(loan30Days.toFixed(2));
      }

  }

  return (
    <div className={s.Form}>
      <form >
      <h4>How much do you need?</h4>
        <input type="text"
              placeholder='$'
              onChange={handleInput} />
        
        <div className={s.LoanOptions}>
          <div className={s.LoanOptionsOne}>
          <h5>To 7 days</h5>
          {total7Days !== '' && <h5>${total7Days}</h5>}
          </div>
          <div className={s.LoanOptionsTwo}>
          <h6>payment dues: {futureDates[1]} </h6>
          </div>
        </div>
        <div className={s.LoanOptions}>
          <div className={s.LoanOptionsOne}>
          <h5>To 14 days</h5>
          {total14Days !== '' && <h5>${total14Days}</h5>}
          </div>
          <div className={s.LoanOptionsTwo}>
          <h6>payment dues: {futureDates[2]} </h6>
          </div>
        </div>
        <div className={s.LoanOptions}>
          <div className={s.LoanOptionsOne}>
          <h5>To 30 days</h5>
          {total30Days !== '' &&<h5>${total30Days}</h5>}
          </div>
          <div className={s.LoanOptionsTwo}>
          <h6>payment dues: {futureDates[3]} </h6>
          </div>
        </div>
        
       
      </form>
    </div>
  )
}

export default Loan