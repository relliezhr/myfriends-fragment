import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable

@Entity
data class MyFriend(
    @PrimaryKey(autoGenerate = true)
    val friendId: Int? = null,
    val nama: String,
    val kelamin: String,
    val email: String,
    val telp: String,
    val alamat: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        if (friendId != null) {
            parcel.writeInt(friendId)
        }
        parcel.writeString(nama)
        parcel.writeString(kelamin)
        parcel.writeString(email)
        parcel.writeString(telp)
        parcel.writeString(alamat)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MyFriend> {
        override fun createFromParcel(parcel: Parcel): MyFriend {
            return MyFriend(parcel)
        }

        override fun newArray(size: Int): Array<MyFriend?> {
            return arrayOfNulls(size)
        }
    }
}
