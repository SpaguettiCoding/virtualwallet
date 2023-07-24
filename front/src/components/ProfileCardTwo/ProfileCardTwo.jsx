import React, { useState } from 'react'
import s from './ProfileCardTwo.module.css'
import img from '../../assets/img1.png'

const ProfileCardTwo = () => {
    const [isActiveSend, setIsActiveSend] = useState(false)
    const [isActiveAdd, setIsActiveAdd] = useState(false)


    const handleSend = () => {
        setIsActiveSend(prevState => !isActiveSend)
    }

    const handleAdd = () => {
        setIsActiveAdd(prevState => !isActiveAdd)
    }

  return (
    <div className={s.ProfileCardTwoWrapper}>
        {isActiveAdd && 
        <div className={s.ModalAdd}>
            
            <div>
            <span>x</span>
            <h3>How much do you want to add?</h3>
            <div className={s.inputButton}>
            <input type="text" placeholder='$' />
            <button onClick={handleAdd}>Ok!</button>
            </div>
            </div>
        </div>}
        <img className={s.img} src={img} alt="movementsimg" />
        <div className={s.Header}>
            <h2>Movements</h2>
            <div>
            {isActiveAdd 
            ? <>
            <span></span>
            </>
            : <button onClick={handleAdd}>Add</button>}
            <button>Send</button>
            </div>
        </div>
        <div className={s.GridContainer}>
        <div className={s.Movements}>
            <div className={s.Initials}>
                <p>MG</p>
            </div>
            <p>8/7/11</p>
            <h3>Magalí Garcia Felice</h3>
            <h3>$50</h3>
        </div>
        <div className={s.Movements}>
            <div className={s.Initials}>
                <p>MG</p>
            </div>
            <p>8/7/11</p>
            <h3>Magalí </h3>
            <h3>$50</h3>
        </div>
        <div className={s.Movements}>
            <div className={s.Initials}>
                <p>MG</p>
            </div>
            <p>8/7/11</p>
            <h3>Magalí Garcia Felice</h3>
            <h3>$50</h3>
        </div>
        <div className={s.Movements}>
            <div className={s.Initials}>
                <p>MG</p>
            </div>
            <p>8/7/11</p>
            <h3>Magalí </h3>
            <h3>$50</h3>
        </div>
        <div className={s.Movements}>
            <div className={s.Initials}>
                <p>MG</p>
            </div>
            <p>8/7/11</p>
            <h3>Magalí Garcia Felice</h3>
            <h3>$50</h3>

        </div>
        <div className={s.Movements}>
            <div className={s.Initials}>
                <p>MG</p>
            </div>
            <p>8/7/11</p>
            <h3>Magalí </h3>
            <h3>$50</h3>
        </div>
        </div>
    </div>
  )
}

export default ProfileCardTwo