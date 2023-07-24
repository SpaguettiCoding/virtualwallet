import profilePic from "../../assets/profilePic.jpg"
import {MdVisibility} from "react-icons/md"
import s from "./ProfileCardOne.module.css"
import ProfileServices from '../ProfileServices/ProfileServices'

const ProfileCardOne = () => {
   

  let  userData = {
        idPic: profilePic,
        name: "Juan", 
        lastName: "Perez", 
        username: "perezjuan"
    }

  return (
    <div className={s.ProfileWrapper}>
        <div className={s.ProfileCard}>
        <img src= {userData.idPic} alt="profilePic" />
        <div>
            <div className={s.ProfileInfo}>
            <h3>{userData.name}</h3>
            <h3 >{userData.lastName}</h3>
            <p >{userData.username}</p>
            </div> 
        </div>
        </div>
        <div className={s.TotalBalance}>
                <div className={s.TotalInfo}>
                    <p>TotalBalance</p>
                    <span>{MdVisibility} </span>
                </div>
                <h1>$54.998</h1>
        </div>

        <ProfileServices />

    </div>
  )
}

export default ProfileCardOne