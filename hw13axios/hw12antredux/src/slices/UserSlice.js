import {createSlice} from '@reduxjs/toolkit'

export const UserSlice = createSlice({
    name: 'User',
    initialState: {
        User: [],
    },
    reducers: {
        set: (state, action) => {
            state.User = action.payload;
        }
    },
})

// Action creators are generated for each case reducer function
export const {set} = UserSlice.actions

export default UserSlice.reducer