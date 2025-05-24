import { createTheme } from "@mui/material";

const themeOptions = {
    palette: {
        mode: 'dark',
        primary: {
            main: '#3f51b5',
        },
        secondary: {
            main: '#f50057',
        },
        background: {
            default: '#a4a5b1',
        }
    },
};

export const defaultTheme = createTheme(themeOptions);