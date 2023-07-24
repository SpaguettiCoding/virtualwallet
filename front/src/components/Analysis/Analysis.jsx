import React from 'react'
import s from './Analysis.module.css'

const Analysis = () => {
  return (
    <div className={s.Analysis}>
      <div>
        <h3>Sent</h3>
        <div className={s.Totals}>
        <h2>$20000</h2>
        </div>
      </div>
      <div>
        <h3>Received</h3>
        <div className={s.Totals}>
        <h2>$1000</h2>
        </div>
      </div>
    </div>
  )
}

export default Analysis