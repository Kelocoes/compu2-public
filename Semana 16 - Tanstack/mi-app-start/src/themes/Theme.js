import { createTheme } from "@mui/material";

const themeOptions = {
    palette: {
        mode: 'light',
        primary: {
            main: '#3f51b5',
        },
        secondary: {
            main: '#f50057',
        },
        background: {
            default: '#ccccd3',
        }
    },
};

export const defaultTheme = createTheme(themeOptions);