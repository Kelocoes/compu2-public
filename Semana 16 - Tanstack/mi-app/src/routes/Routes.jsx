import { createBrowserRouter } from "react-router";
import LandingPage from "../pages/LandingPage";
import AgentsPage from "../pages/AgentsPage";
import AuthComponent from "../Auth/AuthComponent";

const router = createBrowserRouter([
    { path: '/', Component: AuthComponent, children: [
        {
            index: true,
            Component: LandingPage
        },
        {
            path: 'agents',
            Component: AgentsPage,
            children: [
                {
                    path: ':agentId',
                    Component: AgentsPage,
                }
            ]
        }
    ]},
], { basename: '/compu2/front'})

export default router;